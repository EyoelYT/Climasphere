import { env } from './env.js';
import { invertColorOfElementAgainstBg } from './changebackground.js'
import { fetchAndDisplayWeather, changeVideoSource } from './gatherWeatherData.js'

const springHost = env.host;
const springPort = env.port;

document.getElementById('weatherForm').addEventListener('submit', async (event) => {
  event.preventDefault();
  const city = document.getElementById('city').value;
  const queryToPexels = await fetchAndDisplayWeather(city, springHost, springPort);
  try {
    await changeVideoSource(queryToPexels.weather[0].description, springHost, springPort);
  } catch (error) {
    const weatherContainer = document.getElementById('weatherContainer');
    weatherContainer.style.display = 'block';
    document.getElementById('weatherContainer').innerHTML = `<p>${city} not found as a city.</p>`;
  }
});

// Let Header font have dynamic colors
setInterval(() => {
  let header = document.querySelector("header");
  invertColorOfElementAgainstBg(header);
}, 200);

