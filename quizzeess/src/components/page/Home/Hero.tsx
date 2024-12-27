import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";

export const Hero = ({ data }: { data: IQuiz[] }) => {
  return (
    <section className="bg-gradient-to-b from-primary from-70% to-[#ebefff] to-100% p-8 pb-40">
      <div>
        <h1 className="text-2xl text-white">
          Conheça os quizes com mais curtidas
        </h1>
      </div>
      <article className="p-11">
        <Carousel>
          <CarouselContent className="p-4">
            {data.map(({ description, image, name }, i) => (
              <CarouselItem
                key={i}
                className="md:basis-1/2 lg:basis-1/5 max-w-[400px]"
              >
                <div
                  className="flex flex-col items-center gap-5 h-full border border-secondary hover:scale-105 transform hover:cursor-pointer rounded-md text-white
                "
                >
                  <div className="p-5 flex flex-col items-center gap-3">
                    <span className="text-xl font-bold">{name}</span>
                    <p className="text-center max-h-20 overflow-hidden">
                      {description}
                    </p>
                  </div>
                  <div className="mt-auto w-full">
                    <img
                      src={image}
                      alt={name}
                      className="w-full h-[200px] rounded-b-md"
                    />
                  </div>
                </div>
              </CarouselItem>
            ))}
          </CarouselContent>
          <CarouselPrevious />
          <CarouselNext />
        </Carousel>
      </article>
    </section>
  );
};
