const URL_BASE = 'http://localhost:8080/';

const api = axios.create({
    baseURL: URL_BASE,
    headers: {
        'Content-Type': 'application/json',
    }
});