import { IRegister } from "@/pages/register";
import { api } from "../api";

export const registerAPI = async (data: IRegister) => {
  const res = await api.post("/user", {
    ...data,
    birthDate: new Date(data.birthDate),
  });

  return res.data;
};
