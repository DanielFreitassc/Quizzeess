import axios from "axios";

export const api = axios.create({
  baseURL: process.env.NEXT_PUBLIC_BASE_URL,
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("authToken");
  const customConfig = config;

  if (token) customConfig.headers.Authorization = `Bearer ${token}`;

  return customConfig;
});
