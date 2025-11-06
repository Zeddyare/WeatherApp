// kotlin
// File: `app/src/main/java/com/example/weatherapp/models/Weather.kt`
// Domain model used by UI, but annotated for Gson and exposing computed UI properties.
package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("location") private val _location: LocationJson?,
    @SerializedName("current") private val _current: CurrentJson?,
    @SerializedName("forecast") private val _forecast: ForecastContainerJson?
) {
    val location: Location?
        get() = _location?.toDomain()

    val current: Current?
        get() = _current?.toDomain()

    val forecast: List<Forecast>?
        get() = _forecast?.forecastday?.map { it.toDomain() }
}

data class LocationJson(
    @SerializedName("name") val name: String?,
    @SerializedName("region") val region: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("lat") val lat: Double?,
    @SerializedName("lon") val lon: Double?,
    @SerializedName("tz_id") val tzId: String?
) {
    fun toDomain() = Location(
        name = name ?: "Unknown",
        region = region ?: "",
        country = country ?: "",
        lat = lat ?: 0.0,
        lon = lon ?: 0.0,
        tzId = tzId ?: ""
    )
}

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tzId: String
)
data class CurrentJson(
    @SerializedName("temp_c") val tempC: Double?,
    @SerializedName("condition") val condition: ConditionJson?,
    @SerializedName("precip_mm") val precipMm: Double?,
    @SerializedName("wind_kph") val windKph: Double?,
    @SerializedName("wind_dir") val windDir: String?,
    @SerializedName("humidity") val humidity: Int?
) {
    fun toDomain() = Current(
        imageId = normalizeIcon(condition?.icon),
        condition = condition?.text ?: "Unknown",
        temperature = tempC?.let { "%.1f°C".format(it) } ?: "N/A",
        precipitationType = "",
        precipitationAmount = precipMm?.let { "%.1f mm".format(it) } ?: "N/A",
        wind = windKph?.let { "%.0f kph %s".format(it, windDir ?: "") } ?: "N/A",
        humidity = humidity?.let { "$it%" } ?: "N/A"
    )
}

data class ConditionJson(
    @SerializedName("text") val text: String?,
    @SerializedName("icon") val icon: String?
)

data class ForecastContainerJson(
    @SerializedName("forecastday") val forecastday: List<ForecastDayJson>?
)

data class ForecastDayJson(
    @SerializedName("date") val date: String?,
    @SerializedName("day") val day: DayJson?
) {
    fun toDomain() = Forecast(
        date = date ?: "N/A",
        imageId = normalizeIcon(day?.condition?.icon),
        temperatureHigh = day?.maxtempC?.let { "%.1f°C".format(it) } ?: "N/A",
        temperatureLow = day?.mintempC?.let { "%.1f°C".format(it) } ?: "N/A",
        condition = day?.condition?.text ?: "N/A",
        precipitationType = "",
        precipitationAmount = day?.totalPrecipMm?.let { "%.1f mm".format(it) } ?: "N/A",
        precipitationProbability = day?.dailyChanceOfRain?.let { "$it%" } ?: "N/A",
        wind = day?.maxwindKph?.let { "%.0f kph".format(it) } ?: "N/A",
        humidity = day?.avgHumidity?.let { "%.0f%%".format(it) } ?: "N/A"
    )
}

data class DayJson(
    @SerializedName("maxtemp_c") val maxtempC: Double?,
    @SerializedName("mintemp_c") val mintempC: Double?,
    @SerializedName("totalprecip_mm") val totalPrecipMm: Double?,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Int?,
    @SerializedName("avghumidity") val avgHumidity: Double?,
    @SerializedName("maxwind_kph") val maxwindKph: Double?,
    @SerializedName("condition") val condition: ConditionJson?
)

/* UI-facing domain classes (same shape your Composables expect). */
data class Current(
    val imageId: String,
    val condition: String,
    val temperature: String,
    val precipitationType: String,
    val precipitationAmount: String,
    val wind: String,
    val humidity: String
)

data class Forecast(
    val date: String,
    val imageId: String,
    val temperatureHigh: String,
    val temperatureLow: String,
    val condition: String,
    val precipitationType: String,
    val precipitationAmount: String,
    val precipitationProbability: String,
    val wind: String,
    val humidity: String
)

/* helper */
private fun normalizeIcon(icon: String?): String {
    if (icon.isNullOrBlank()) return ""
    return when {
        icon.startsWith("//") -> "https:$icon"
        icon.startsWith("http") -> icon
        else -> "https://$icon"
    }
}