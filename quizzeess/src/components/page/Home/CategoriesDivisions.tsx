import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";
import Image from "next/image";

interface IQuizCategories {
  title: string;
  quiz: IQuiz[];
}

export const CategoriesDivisions = ({ title, quiz }: IQuizCategories) => {
  return (
    <div className="p-8">
      <span className="text-xl">{title}</span>
      <article className="p-11">
        <Carousel>
          <CarouselContent className="p-4">
            {quiz.map(({ description, image, name }, i) => (
              <CarouselItem
                key={i}
                className="md:basis-1/2 lg:basis-1/5 max-w-[400px]"
              >
                <div
                  className="flex flex-col items-center gap-5 h-full border border-primary hover:scale-105 transform hover:cursor-pointer rounded-md
                "
                >
                  <div className="p-5 flex flex-col items-center gap-3">
                    <span className="text-xl font-bold">{name}</span>
                    <p className="text-center max-h-20 overflow-hidden">
                      {description}
                    </p>
                  </div>
                  <div className="mt-auto w-full">
                    <Image
                      src={image}
                      alt={name}
                      className="w-full h-[200px] rounded-b-md"
                      width={400}
                      height={200}
                    />
                  </div>
                </div>
              </CarouselItem>
            ))}
          </CarouselContent>
          <CarouselPrevious className="border border-primary text-primary hover:bg-primary hover:text-white" />
          <CarouselNext className="border border-primary text-primary hover:bg-primary hover:text-white" />
        </Carousel>
      </article>
    </div>
  );
};
