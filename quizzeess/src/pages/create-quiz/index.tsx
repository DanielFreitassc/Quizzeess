import { NestedAlternative } from "@/components/page/CreateQuiz/NestedAlternative";
import { Button } from "@/components/ui/button";
import {
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Textarea } from "@/components/ui/textarea";
import { useToast } from "@/hooks/use-toast";
import { quizSchema, TQuiz } from "@/utils/schemas/create-quiz.schema";
import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect } from "react";
import { FormProvider, useFieldArray, useForm } from "react-hook-form";

const CreateQuiz = () => {
  const { toast } = useToast();
  const form = useForm<TQuiz>({
    resolver: zodResolver(quizSchema),
    defaultValues: {
      category: "",
      description: "",
      image: "",
      name: "",
      questions: [
        {
          statement: "Exemplo de enunciado da questão",
          correctAlternative: "",
          alternative: [
            { alternativeLetter: "A", body: "Exemplo 1 de alternativa" },
            { alternativeLetter: "B", body: "Exemplo 2 de alternativa" },
          ],
        },
      ],
    },
  });

  const {
    append: questionsAppend,
    fields: questionsArray,
    remove: questionsRemove,
  } = useFieldArray({
    name: "questions",
    control: form.control,
  });

  const handleCreateQuestion = (data: TQuiz) => {
    console.dir(data);
  };

  useEffect(() => {
    if (form.formState.errors.questions?.root?.message)
      toast({
        title: form.formState.errors.questions?.root?.message,
        variant: "destructive",
      });
  }, [form.formState.errors, toast]);

  return (
    <div>
      <h1>Crie seu próprio quiz!</h1>
      <FormProvider {...form}>
        <form
          onSubmit={form.handleSubmit(handleCreateQuestion)}
          className="flex flex-col gap-5 p-4"
        >
          <div className="flex flex-col gap-3 bg-white p-4 rounded-md">
            <FormField
              name="name"
              control={form.control}
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Titulo do quiz:</FormLabel>
                  <FormControl>
                    <Input {...field} placeholder="" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />

            <FormField
              name="description"
              control={form.control}
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Descrição:</FormLabel>
                  <FormControl>
                    <Textarea
                      {...field}
                      placeholder=""
                      className="resize-none"
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <div className="flex gap-4 w-full">
              <FormField
                name="image"
                control={form.control}
                render={({ field }) => (
                  <FormItem className="w-full">
                    <FormLabel>Foto de apresentação:</FormLabel>
                    <FormControl>
                      <Input type="file" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />

              <FormField
                name="category"
                control={form.control}
                render={({ field }) => (
                  <FormItem className="w-full">
                    <FormLabel>Categoria:</FormLabel>
                    <FormControl>
                      <Select
                        value={field.value}
                        onValueChange={field.onChange}
                      >
                        <SelectTrigger>
                          <SelectValue placeholder="Selecione" />
                        </SelectTrigger>
                        <SelectContent>
                          <SelectGroup>
                            <SelectItem value="teste">Teste</SelectItem>
                          </SelectGroup>
                        </SelectContent>
                      </Select>
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
          </div>

          <div className="flex flex-col gap-4">
            {questionsArray.map(({ id }, i) => (
              <div
                key={id}
                className="flex flex-col gap-4 p-4 bg-white rounded-md"
              >
                <Button type="button" onClick={() => questionsRemove(i)}>
                  Deletar questão
                </Button>
                <FormField
                  name={`questions.${i}.statement`}
                  control={form.control}
                  render={({ field }) => (
                    <FormItem>
                      <FormControl>
                        <span className="flex gap-4 px-2 items-center">
                          {i + 1}) <Input {...field} />
                        </span>
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <NestedAlternative questionIndex={i} />
                {form.formState.errors?.questions?.[i]?.message && (
                  <p>{form.formState.errors.questions[i].message}</p>
                )}
              </div>
            ))}
          </div>
          <Button
            type="button"
            onClick={() =>
              questionsAppend({
                statement: `Nova questão`,
                alternative: [
                  { alternativeLetter: "A", body: "Exemplo 1 de alternativa" },
                  { alternativeLetter: "B", body: "Exemplo 2 de alternativa" },
                ],
                correctAlternative: "",
              })
            }
          >
            Adicionar questão
          </Button>

          <Button>Criar quiz</Button>
        </form>
      </FormProvider>
    </div>
  );
};

export default CreateQuiz;
