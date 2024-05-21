package data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.coroutines.flow.asFlow

data class LocationStructure (
    val location_lat: Double,
    val location_lag: Double,
    val name: String,
    val information: String
)
class Location() {
    val listLocation = listOf(
    LocationStructure(53.18954522460419,
        50.12189715876126,
        "stadion Locomativ",
        "на этом стадионе я тренировался 6 лет"
    ),
        LocationStructure(53.18951951102484,
            50.1836523387066,
            "school_121",
            "школа в которой я учился"
    ),
        LocationStructure(53.19152035172246,
            50.18957882303026,
            "Avrora Moll",
            "ближайший торговый центр к\nмоему прошлому дому"
        ),
        LocationStructure(53.18622462384662,
            50.123171545553035,
            "railway station",
            "caмый большой вокзал в Европпе"
        ),
        LocationStructure(53.20771600127487,
            50.10912838147074,
            "embankment",
            "самая длинная набережная в европпе"
        )
        )
   fun getObjects() =listLocation

}
