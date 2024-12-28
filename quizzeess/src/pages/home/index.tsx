import { Footer } from "@/components/Layout/Footer";
import { Header } from "@/components/Layout/Header";
import { CategoriesDivisions } from "@/components/page/Home/CategoriesDivisions";
import CreateQuiz from "@/components/page/Home/CreateQuiz";
import { Hero } from "@/components/page/Home/Hero";
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
      {data && !isLoading && (
        <div>
          <Header />
          <Hero data={data} />
          <h3 className="text-2xl p-8">
            Veja os melhores quizes separado por categoria
          </h3>
          <CategoriesDivisions quiz={data} title="Paises" />
          <CategoriesDivisions quiz={data} title="Programação" />
          <CategoriesDivisions quiz={data} title="História" />
          <CategoriesDivisions quiz={data} title="Jogos" />
          <CreateQuiz />
          <Footer />
        </div>
      )}
    </div>
  );
};

export default Home;
