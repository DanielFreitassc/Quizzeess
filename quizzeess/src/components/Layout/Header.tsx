import Image from "next/image";
import logo from "../../../public/logo.svg";
import { Button } from "../ui/button";
import { useRouter } from "next/router";

export const Header = () => {
  const { push } = useRouter();

  return (
    <header className="sticky top-0 z-10 flex justify-between items-center w-full h-24 bg-primary px-20 shadow-xl">
      <div>
        <Image src={logo} alt="logo" width={60} height={60} />
      </div>
      <div>
        <Button
          variant="outline"
          className="text-white"
          onClick={() => push("/login")}
        >
          Entrar
        </Button>
        <Button
          variant="link"
          className="text-white"
          onClick={() => push("/register")}
        >
          Cadastrar-se
        </Button>
      </div>
    </header>
  );
};
