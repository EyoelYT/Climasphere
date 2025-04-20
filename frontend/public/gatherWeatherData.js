export async function fetchAndDisplayWeather(city, host, port) {
  try {
    const response = await fetch(`http://${host}:${port}/api/weather?city=${city}`);
    if (!response.ok) {
      throw new Error(`Error: ${response.status}`);
    }
    const data = await response.json();
    displayWeather(data);
    return data;
  } catch (error) {
    document.getElementById('weatherContainer').innerHTML = `<p>\n${error.message}</p>`;
    return { error: 'error' };
  }
}

export function displayWeather(data) {
  const weatherContainer = document.getElementById('weatherContainer');
  weatherContainer.style.display = 'block';
  weatherContainer.innerHTML = `
    <h2>Weather in ${data.name}</h2>
    <p>Temperature: ${data.main.temp}°C</p>
    <p>Humidity: ${data.main.humidity}%</p>
    <p>Condition: ${data.weather[0].description}</p>
  `;
}

export async function changeVideoSource(weatherVideoQuery, host, port) {
  let backgroundVideoElement = document.getElementById('video-container');
  let videoSource = document.getElementById('background-video-source');

  let pexelVideoUrlResponse = await fetch(`http://${host}:${port}/api/pexels?query=${weatherVideoQuery}`);
  let pexelVideoUrl = await pexelVideoUrlResponse.text();

  videoSource.setAttribute('src', `${pexelVideoUrl}`);
  videoSource.setAttribute('type', 'video/mp4');

  backgroundVideoElement.load()
  backgroundVideoElement.play()
}
