package presintation

import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.module_2_17_permissions.R
import com.example.module_2_17_permissions.databinding.FragmentSecondBinding
import data.App
import data.FailDao
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor

private const val FILENAME_FORMAT = "dd.MM.yyyy (HH:mm)"
class SecondFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val photoDao: FailDao = (activity?.application as App).db.failDao()
                return MainViewModel(photoDao) as T
            }
        }
    }

    private var dataPhoto = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
        .format(System.currentTimeMillis())
    private var launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted ->
        Toast.makeText(requireContext(), "permission is $isGranted", Toast.LENGTH_SHORT).show()
    }
    private var imageCapture: ImageCapture? = null
    private lateinit var executor: Executor

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        executor = ContextCompat.getMainExecutor(requireContext())
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        checkPermissions()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissions()
        binding.previewView.setOnClickListener { takePhoto() }
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_mainFragment)
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            startCamera()
            Toast.makeText(requireContext(), "permission is Granted", Toast.LENGTH_SHORT).show()
        }
        while (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            launcher.launch(android.Manifest.permission.CAMERA)
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                startCamera()
                Toast.makeText(requireContext(), "permission is Granted", Toast.LENGTH_SHORT).show()
                break
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
            preview.setSurfaceProvider(binding.previewView.surfaceProvider)
            imageCapture = ImageCapture.Builder().build()

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageCapture
            )
        }, executor)

    }


    private fun takePhoto() {
        val imageCapture2 = imageCapture?: return

        dataPhoto = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, dataPhoto)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                requireActivity().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            ).build()

        imageCapture2.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(requireContext(), "photo saved ${outputFileResults.savedUri}" , Toast.LENGTH_SHORT).show()
                    // сохранние из галереи в БД
                   viewModel.savePhoto(outputFileResults.savedUri.toString(), dataPhoto)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(requireContext(), "photo failed ${exception.message}", Toast.LENGTH_SHORT).show()
                    exception.printStackTrace()
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}