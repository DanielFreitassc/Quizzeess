import { JwtPayload } from "jwt-decode";

export interface IAuthContext {
  signIn: (token: string | null) => void;
  signOut: () => void;
  user: JwtPayload | null;
}
