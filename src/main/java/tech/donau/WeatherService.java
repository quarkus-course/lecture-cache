package tech.donau;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import tech.donau.data.Weather;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class WeatherService {

    @CacheResult(cacheName = "weather_status")
    public Weather provideWeather(@CacheKey String city) {
        final String weatherStatus = getWeatherStatus();
        return new Weather(city, weatherStatus);
    }

    @CacheInvalidate(cacheName = "weather_status")
    public void invalidate(String city) { }

    @CacheInvalidateAll(cacheName = "weather_status")
    public void invalidateAll() { }

    public String getWeatherStatus() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextBoolean() ? "Sunny" : "Rainy";
    }
}
