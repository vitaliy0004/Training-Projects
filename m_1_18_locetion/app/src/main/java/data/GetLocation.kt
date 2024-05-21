package data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class GetLocation() {
  fun getObjectList(): List<LocationStructure> = Location().getObjects()
}