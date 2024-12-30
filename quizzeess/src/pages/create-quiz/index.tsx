import { NestedAlternative } from "@/components/page/CreateQuiz/NestedAlternative";
import { Button } from "@/components/ui/button";
import {
  Form,
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
          statement: "Exemplo de questão",
          correctAlternative: "",
          alternative: [
            { alternativeLetter: "a", body: "Exemplo 1" },
            { alternativeLetter: "b", body: "Exemplo 2" },
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
    console.log("submitted", data);
  };

  console.log(form.formState.errors);

  useEffect(() => {
    if (form.formState.errors.root?.message)
      toast({
        title: form.formState.errors.root.message,
      });
  }, [form.formState.errors, toast]);

  return (
    <div>
      <h1>Crie seu próprio quiz!</h1>
      <FormProvider {...form}>
        <Form {...form}>
          <form onClick={form.handleSubmit(handleCreateQuestion)}>
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

            <FormField
              name="image"
              control={form.control}
              render={({ field }) => (
                <FormItem>
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
                <FormItem>
                  <FormLabel>Categoria:</FormLabel>
                  <FormControl>
                    <Select
                      value={field.value} // Liga o valor ao campo do formulário
                      onValueChange={field.onChange} // Atualiza o valor do formulário ao selecionar uma opção
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

            <div>
              {questionsArray.map(({ id }, i) => (
                <div key={id}>
                  <span>
                    {i + 1}){" "}
                    <Input {...form.register(`questions.${i}.statement`)} />
                  </span>
                  <NestedAlternative questionIndex={i} />
                  <Button type="button" onClick={() => questionsRemove(i)}>
                    Deletar questão
                  </Button>
                  {form.formState.errors?.questions?.[i]?.message && (
                    <p>{form.formState.errors.questions[i].message}</p>
                  )}
                </div>
              ))}
              <Button
                type="button"
                onClick={() =>
                  questionsAppend({
                    statement: `Nova questão`,
                    alternative: [],
                    correctAlternative: "",
                  })
                }
              >
                Adicionar questão
              </Button>
            </div>

            <Button>Criar</Button>
          </form>
        </Form>
      </FormProvider>
    </div>
  );
};

export default CreateQuiz;
