import Image from "next/image";
import mouse from "../../../../public/mouse.jpg";
import { Button } from "@/components/ui/button";

const CreateQuiz = () => {
  return (
    <section className="flex justify-center items-center gap-56 py-3 pb-28">
      <div className="max-w-[520px] flex flex-col gap-6">
        <h3 className="text-3xl">
          Crie Seus Próprios Quizzes Personalizados! 🎉
        </h3>
        <p className="text-lg">
          Imagine a possibilidade de criar quizzes do seu jeito, com até 20
          perguntas e 5 alternativas por questão. Seja para educar, engajar, ou
          simplesmente entreter, agora você tem o controle total para criar algo
          único e inesquecível!
        </p>
        <Button className="max-w-[200px]">Criar</Button>
      </div>
      <div>
        <Image src={mouse} alt="mouse ilustrativo" width={600} height={600} />
      </div>
    </section>
  );
};

export default CreateQuiz;
