import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { alternativeLetterGenerate } from "@/utils/fuctions";
import { TQuiz } from "@/utils/schemas/create-quiz.schema";
import { Plus } from "lucide-react";
import { useFieldArray, useFormContext } from "react-hook-form";

export const NestedAlternative = ({
  questionIndex,
}: {
  questionIndex: number;
}) => {
  const { control, setError, register, setValue } = useFormContext<TQuiz>();

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

  const setALternativeLetter = (alternativeIndex: number) => {
    setValue(
      `questions.${questionIndex}.alternative.${alternativeIndex}.alternativeLetter`,
      alternativeLetterGenerate(alternativeIndex)
    );
  };

  return (
    <div>
      {alternativeArray.map(({ id }, i) => {
        setALternativeLetter(i);
        return (
          <div key={id}>
            <span>{alternativeLetterGenerate(i)})</span>
            <Input
              {...register(`questions.${questionIndex}.alternative.${i}.body`)}
            />
            <Button onClick={() => alternativeRemove(i)}>Deletar</Button>
          </div>
        );
      })}

      {alternativeArray.length < 5 && (
        <div>
          <Button type="button" onClick={arrayLengthVerify}>
            Adicionar alternativa
            <Plus />
          </Button>
        </div>
      )}
    </div>
  );
};
