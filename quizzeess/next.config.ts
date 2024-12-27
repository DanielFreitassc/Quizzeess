import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  reactStrictMode: true,
  images: {
    remotePatterns: [
      {
        hostname: "loremflickr.com",
        protocol: "https",
      },
    ],
  },
};

export default nextConfig;
