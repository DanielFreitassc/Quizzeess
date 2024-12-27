import axios from "axios";

export const getAllQuiz = async (): Promise<IQuiz> => {
  const res = await axios.get(
    "https://65e61b98d7f0758a76e81f40.mockapi.io/financa/data"
  );

  return res.data;
};
