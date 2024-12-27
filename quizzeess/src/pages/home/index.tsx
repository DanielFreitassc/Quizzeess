import { Carousel } from "@/components/ui/carousel";
import { getAllQuiz } from "@/services/quiz/get-all-quiz";
import { useQuery } from "@tanstack/react-query";

const Home = () => {
  const { data, isLoading } = useQuery({
    queryKey: ["get-all-quizes"],
    staleTime: 180000, // 3 min
    queryFn: async () => getAllQuiz(),
  });

  return (
    <div>
      {isLoading && <p>loading...</p>}
      <Carousel></Carousel>
    </div>
  );
};

export default Home;
