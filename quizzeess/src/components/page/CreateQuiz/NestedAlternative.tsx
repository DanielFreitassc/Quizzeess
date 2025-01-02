import { Button } from "@/components/ui/button";
import {
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
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
  const {
    control,
    setError,
    setValue,
    formState: { errors },
  } = useFormContext<TQuiz>();

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
    <div className="bg-white p-2 flex flex-col gap-2">
      {alternativeArray.map(({ id }, i) => {
        setALternativeLetter(i);
        const letter = alternativeLetterGenerate(i);
        return (
          <FormField
            key={id}
            name={`questions.${questionIndex}.alternative.${i}.body`}
            control={control}
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <div className="flex items-center gap-3">
                    <div>
                      <Input
                        type="radio"
                        value={letter}
                        name={`question${questionIndex}`}
                        onChange={(evt) => {
                          setValue(
                            `questions.${questionIndex}.correctAlternative`,
                            evt.target.value
                          );
                        }}
                      />
                    </div>
                    <span>{letter})</span>
                    <Input {...field} />
                    <Button onClick={() => alternativeRemove(i)}>
                      Deletar
                    </Button>
                  </div>
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
        );
      })}
      {alternativeArray.length < 5 && (
        <div className="flex items-center gap-5">
          <Button type="button" onClick={arrayLengthVerify}>
            Adicionar alternativa
            <Plus />
          </Button>

          {errors.questions?.[questionIndex]?.alternative?.root?.message && (
            <p className="text-xs text-[#ef4444]">
              {errors.questions[questionIndex].alternative.root.message}
            </p>
          )}
          {errors.questions?.[questionIndex]?.correctAlternative?.message && (
            <p className="text-xs text-[#ef4444]">
              {errors.questions[questionIndex].correctAlternative.message}
            </p>
          )}
        </div>
      )}
    </div>
  );
};
