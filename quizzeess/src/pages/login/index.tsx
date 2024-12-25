import { Button } from "@/components/ui/button";
import {
  Form,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { zodResolver } from "@hookform/resolvers/zod";
import Image from "next/image";
import { useForm } from "react-hook-form";
import { z } from "zod";
import computer from "../../../public/loginComputer.svg";
import { Input } from "@/components/ui/input";
import logo from "../../../public/logo.svg";
import { useRouter } from "next/router";

const schema = z.object({
  username: z.string().min(1, "Campo obrigatório"),
  password: z.string().min(1, "Campo obrigatório"),
});

type TFormData = z.infer<typeof schema>;

const Login = () => {
  const { push } = useRouter();

  const form = useForm<TFormData>({
    resolver: zodResolver(schema),
    defaultValues: {
      username: "",
      password: "",
    },
  });

  const handleLogin = (data: TFormData) => {
    console.log(data);
  };

  return (
    <div className="flex">
      <div className="p-8 relative w-full lg:w-2/4 lg:p-0 flex flex-col justify-center items-center lg:items-end gap-6 overflow-hidden">
        <div className="absolute -bottom-20 left-9 bg-primary rounded-full lg:h-72 xl:h-[400px] transform rotate-45 lg:w-36 xl:w-60" />
        <div className="flex items-center justify-between md:max-w-[300px] xl:max-w-[400px] w-full">
          <h1 className="xl:text-3xl text-primary">Login</h1>
          <Image src={logo} alt="logo" className="w-1/3" />
        </div>
        <Form {...form}>
          <form
            className="md:max-w-[300px] xl:max-w-[400px] w-full flex flex-col gap-5"
            onSubmit={form.handleSubmit(handleLogin)}
          >
            <FormField
              name="username"
              control={form.control}
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Nome de usuário:</FormLabel>
                  <Input placeholder="Digite seu nome de usuário" {...field} />
                  <FormMessage />
                </FormItem>
              )}
            />

            <FormField
              name="password"
              control={form.control}
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Senha:</FormLabel>
                  <Input placeholder="Digite sua senha" {...field} />
                  <FormMessage />
                </FormItem>
              )}
            />

            <div className="flex flex-col w-full gap-4">
              <Button type="submit">Entrar</Button>
              <div className="flex items-center justify-center">
                <span className="text-sm">Não tem conta?</span>
                <Button
                  type="button"
                  variant="link"
                  onClick={() => push("/register")}
                >
                  Cadastra-se
                </Button>
              </div>
            </div>
          </form>
        </Form>
      </div>
      <div className="hidden relative lg:flex w-2/3">
        <Image
          src={computer}
          alt="computador"
          className="absolute inset-0 m-auto"
          priority
        />
        <div className="h-screen w-2/4"></div>
        <div className="bg-primary h-screen w-2/4"></div>
      </div>
    </div>
  );
};

export default Login;
