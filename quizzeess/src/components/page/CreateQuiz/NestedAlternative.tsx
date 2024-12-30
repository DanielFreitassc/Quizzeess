import { Button } from "@/components/ui/button";
import { alternativeLetterGenerate } from "@/utils/fuctions";
import { TQuiz } from "@/utils/schemas/create-quiz.schema";
import { Plus } from "lucide-react";
import { useEffect } from "react";
import { useFieldArray, useFormContext } from "react-hook-form";

export const NestedAlternative = ({
  questionIndex,
}: {
  questionIndex: number;
}) => {
  const { control, setError } = useFormContext<TQuiz>();

  const {
    append: alternativeAppend,
    fields: alternativeArray,
    remove: alternativeRemove,
  } = useFieldArray({
    name: `questions.${questionIndex}.alternative`,
    control,
    rules: {
      maxLength: 5,
    },
  });

  const arrayLengthVerify = () => {
    if (alternativeArray.length === 5) {
      setError(`questions.${questionIndex}.alternative`, {
        message: "No maximo 5 alternativas",
        type: "custom",
      });
      return;
    }

    alternativeAppend({
      alternativeLetter: "",
      body: "",
    });
  };

  return (
    <div>
      {alternativeArray.map(({ id, body, alternativeLetter }, i) => (
        <div key={id}>
          <span>
            {alternativeLetterGenerate(i)}) {body}
          </span>
        </div>
      ))}
      <div>
        <Button type="button" onClick={arrayLengthVerify}>
          Adicionar alternativa
          <Plus />
        </Button>
      </div>
    </div>
  );
};
