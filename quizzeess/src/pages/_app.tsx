import { Toaster } from "@/components/ui/toaster";
import { AuthProvider } from "@/context/Auth/AuthProvider";
import "@/styles/globals.css";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import type { AppProps } from "next/app";

const queryClient = new QueryClient();

export default function App({ Component, pageProps }: AppProps) {
  return (
    <QueryClientProvider client={queryClient}>
      <AuthProvider>
        <Toaster />
        <Component {...pageProps} />
      </AuthProvider>
    </QueryClientProvider>
  );
}
