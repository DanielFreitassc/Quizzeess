import { z } from "zod";

export const alternativeSchema = z.array(
  z.object({
    alternativeLetter: z.string().min(1, "Campo obrigatório"),
    body: z.string().min(1, "Campo obrigatório"),
  })
);

const questionsSchema = z
  .array(
    z.object({
      statement: z.string().min(1, "Campo obrigatório"),
      alternative: alternativeSchema,
      correctAlternative: z.string().min(1, "Campo obrigatório"),
    })
  )
  .min(5, "Deve conter ao menos 5 questões");

export const quizSchema = z.object({
  name: z.string().min(1, "Campo obrigatório"),
  description: z
    .string()
    .min(1, "Campo obrigatório")
    .max(200, "Maximo de 200 caracteres"),
  image: z.string().min(1, "Campo obrigatório"),
  category: z.string().min(1, "Campo obrigatório"),
  questions: questionsSchema,
});

export type TQuiz = z.infer<typeof quizSchema>;
