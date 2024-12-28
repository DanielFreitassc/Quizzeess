interface TDataFooter {
  [key: string]: {
    label: string;
    linkTo: string;
  }[];
}

const dataFooter: TDataFooter = {
  Quizzeess: [
    { label: "Criar conta", linkTo: "/" },
    { label: "Criar quiz", linkTo: "/" },
    { label: "Jogar quizes", linkTo: "/" },
    { label: "Jogar quiz aleatorio", linkTo: "/" },
  ],
  "Sobre nós": [
    { label: "Contato", linkTo: "/" },
    { label: "Termos", linkTo: "/" },
  ],
};

export const Footer = () => {
  return (
    <footer className="bg-primary p-8">
      <div className="flex gap-14">
        {Object.entries(dataFooter).map(([title, topics]) => (
          <div key={title} className="text-white flex flex-col gap-4">
            <h3 className="font-bold text-lg">{title}</h3>
            <ul className="flex flex-col gap-4">
              {topics.map(({ label, linkTo }) => (
                <ul key={label} className="hover:underline text-lg">
                  <a href={linkTo}>{label}</a>
                </ul>
              ))}
            </ul>
          </div>
        ))}
      </div>
      <div className="border-b-2 border-white p-5" />
      <span className="text-white text-lg flex justify-center">
        &copy; Quizzeess {new Date().getFullYear()}
      </span>
    </footer>
  );
};
