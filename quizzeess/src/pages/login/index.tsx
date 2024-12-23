import { Button } from "@/components/ui/button";
import { Form } from "@/components/ui/form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";

const schema = z.object({
  username: z.string().min(1, "Campo obrigatório"),
  password: z.string().min(1, "Campo obrigatório"),
});

type TFormData = z.infer<typeof schema>;

const Login = () => {
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
    <div>
      <div>
        <Form {...form}>
          <form onSubmit={form.handleSubmit(handleLogin)}>
            {/* <div>
              <Input
                label="Username"
                {...register("username")}
                errorMessage={errors.username?.message}
                isInvalid={!!errors.username?.message}
              />
            </div>
            <div>
              <Input
                label="Senha"
                type="password"
                {...register("password")}
                errorMessage={errors.password?.message}
                isInvalid={!!errors.password?.message}
              />
            </div> */}
            <div>
              <Button type="submit">Entrar</Button>
              <Button variant="link">stre-se</Button>
            </div>
          </form>
        </Form>
      </div>
    </div>
  );
};

export default Login;
