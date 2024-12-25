import { useState } from "react";
import { AuthContext } from "./AuthContext";
import { jwtDecode, JwtPayload } from "jwt-decode";

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [user, setUser] = useState<JwtPayload | null>(null);

  const signIn = (token: string | null) => {
    if (!token) return;
    const decoded = jwtDecode(token);
    localStorage.setItem("authToken", token);

    setUser(decoded);
  };

  const signOut = () => {
    setUser(null);
    localStorage.removeItem("authToken");
  };

  return (
    <AuthContext.Provider value={{ signIn, signOut, user }}>
      {children}
    </AuthContext.Provider>
  );
};
