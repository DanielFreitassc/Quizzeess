import Image from "next/image";
import React, { useState } from "react";
import computer from "../../../public/computer.png";
import logo from "../../../public/logo.svg";
import { useForm } from "react-hook-form";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Button } from "@/components/ui/button";
import { supabase } from "@/config/supabase";
import { v4 as uuidv4 } from "uuid";
import { useToast } from "@/hooks/use-toast";

const schema = z
  .object({
    fullName: z.string().min(3, "No minimo 3 caracteres"),
    username: z.string().min(3, "No minimo 3 caracteres"),
    birthDate: z.string().min(1, "Campo obrigatório"),
    password: z.string().min(1, "Campo obrigatório"),
    confirmPassword: z.string().min(1, "Campo obrigatório"),
    image: z.string().min(1, "Campo obrigatório"),
    language: z.string().min(1, "Campo obrigatório"),
  })
  .superRefine((val, ctx) => {
    if (val.confirmPassword !== val.password) {
      ctx.addIssue({
        code: z.ZodIssueCode.custom,
        message: "Os campo de senha e confirmar senha deve ser iguais",
        path: ["confirmPassword"],
      });
    }
  });

type TFormData = z.infer<typeof schema>;

const Register = () => {
  const { toast } = useToast();
  const [file, setFile] = useState<File>();

  const form = useForm<TFormData>({
    resolver: zodResolver(schema),
    defaultValues: {
      birthDate: "",
      confirmPassword: "",
      fullName: "",
      image: "",
      language: "",
      password: "",
      username: "",
    },
  });

  const handleRegister = async (data: TFormData) => {
    console.log(data);
    const uuid = uuidv4();
    if (!file) {
      form.setError("image", {
        type: "custom",
        message: "Imagem de perfil obrigatória",
      });
      return;
    }

    const { data: dataSupabase, error } = await supabase.storage
      .from("images")
      .upload(`user/${uuid}`, file, {
        upsert: false,
      });

    if (error) {
      toast({
        title: "Erro ao enviar a imagem!",
        variant: "destructive",
      });
      return;
    }

    console.log(data);
  };

  return (
    <div className="flex max-w-[2000px]">
      <div className="hidden relative w-2/4  h-screen lg:flex justify-end items-center xl:left-14">
        <div className="absolute lg:max-w-[300px] xl:max-w-[600px] w-full bg-[#AFB3FF] h-3/4 rounded-b-[200px] top-0 left-32 z-10 shadow-sm" />
        <div className="absolute left-7 -top-10 lg:max-w-[300px] xl:max-w-[600px] w-full bg-[#656ED3] h-3/4 rounded-b-full z-0 border-transparent rotate-6 " />
        <Image
          src={computer}
          alt="Computador"
          priority
          className="aspect-square xl:h-[700px] xl:max-w-[700px] w-full relative z-20 pointer-events-none"
        />
      </div>
      <div className="relative z-40 w-full p-11 lg:p-0 lg:w-2/4 flex flex-col lg:mt-10 justify-center lg:justify-start lg:items-center lg:gap-14">
        <div className="flex flex-row-reverse lg:flex-row justify-between  gap-16 items-center ">
          <Image
            src={logo}
            alt="logo"
            className="w-full max-w-16 xl:max-w-24 pointer-events-none"
          />
          <h1 className="text-lg lg:text-lg xl:text-3xl text-[#656ED3]">
            Cadastre-se
          </h1>
        </div>
        <Form {...form}>
          <form
            className="flex flex-col gap-3"
            onSubmit={form.handleSubmit(handleRegister)}
          >
            <div className="flex flex-col lg:flex-row gap-2">
              <FormField
                name="fullName"
                control={form.control}
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Nome completo:</FormLabel>
                    <FormControl>
                      <Input {...field} placeholder="Informe seu nome" />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />

              <FormField
                name="username"
                control={form.control}
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Nome de usuário:</FormLabel>
                    <FormControl>
                      <Input
                        {...field}
                        placeholder="Informe seu Nome de usuario"
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <div className="flex flex-col lg:flex-row gap-2">
              <FormField
                name="birthDate"
                control={form.control}
                render={({ field }) => (
                  <FormItem className="w-full">
                    <FormLabel>Data de nascimento:</FormLabel>
                    <FormControl>
                      <Input
                        {...field}
                        type="date"
                        placeholder="Informe seu Nome de usuario"
                        className="w-full"
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />

              <FormField
                name="language"
                control={form.control}
                render={({ field }) => (
                  <FormItem className="w-full">
                    <FormLabel>Idioma:</FormLabel>
                    <FormControl>
                      <Select
                        onValueChange={field.onChange}
                        value={field.value}
                      >
                        <SelectTrigger className="w-full">
                          <SelectValue placeholder="Seu idioma" />
                        </SelectTrigger>
                        <SelectContent>
                          <SelectItem value="portuguese">Português</SelectItem>
                        </SelectContent>
                      </Select>
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <FormField
              name="image"
              control={form.control}
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Foto de perfil:</FormLabel>
                  <FormControl>
                    <Input
                      {...field}
                      type="file"
                      placeholder="Insira sua foto"
                      onChange={(evt) => {
                        const selectedFile = evt.target.files?.[0];
                        setFile(selectedFile);
                        field.onChange(evt.target.value);
                      }}
                    />
                  </FormControl>
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
                  <FormControl>
                    <Input
                      {...field}
                      type="password"
                      placeholder="Insira sua senha"
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />

            <FormField
              name="confirmPassword"
              control={form.control}
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Confirme sua senha:</FormLabel>
                  <FormControl>
                    <Input
                      {...field}
                      type="password"
                      placeholder="Digite sua senha novamente"
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <Button type="submit">Cadastrar</Button>
          </form>
        </Form>
      </div>
    </div>
  );
};

export default Register;
