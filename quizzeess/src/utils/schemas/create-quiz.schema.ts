import { z } from "zod";

export const alternativeSchema = z
  .array(
    z.object({
      alternativeLetter: z.string().min(1, "Campo obrigatório"),
      body: z.string().min(1, "Campo obrigatório"),
    })
  )
  .refine((val) => val.length >= 2, {
    message: "É necessário de 2 alternativa no mínimo",
  });

const questionsSchema = z
  .array(
    z.object({
      statement: z.string().min(1, "Campo obrigatório"),
      alternative: alternativeSchema,
      correctAlternative: z
        .string()
        .min(1, "Alternativa sem resposta correta selecionada"),
    })
  )
  .min(5, "Deve conter ao menos 5 questões")
  .max(20, "Deve conter no maximo 20 questões");

export const quizSchema = z.object({
  name: z.string().min(1, "Campo obrigatório"),
  description: z
    .string()
    .min(1, "Campo obrigatório")
    .max(200, "Maximo de 200 caracteres"),
  image: z.string(),
  category: z.string().min(1, "Campo obrigatório"),
  questions: questionsSchema,
});

export type TQuiz = z.infer<typeof quizSchema>;
