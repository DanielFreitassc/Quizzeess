import { NestedAlternative } from "@/components/page/CreateQuiz/NestedAlternative";
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
import { quizSchema, TQuiz } from "@/utils/schemas/create-quiz.schema";
import { zodResolver } from "@hookform/resolvers/zod";
import { FormProvider, useFieldArray, useForm } from "react-hook-form";

const CreateQuiz = () => {
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

  return (
    <div>
      <h1>Crie seu próprio quiz!</h1>
      <FormProvider {...form}>
        <Form {...form}>
          <form>
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
                    <Select onOpenChange={field.onChange} value={field.value}>
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
              {questionsArray.map(
                ({ correctAlternative, id, statement }, i) => (
                  <div key={id}>
                    <span>
                      {i + 1}){statement}
                    </span>
                    <NestedAlternative questionIndex={i} />
                  </div>
                )
              )}
            </div>
          </form>
        </Form>
      </FormProvider>
    </div>
  );
};

export default CreateQuiz;
