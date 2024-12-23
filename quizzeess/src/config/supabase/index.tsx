import { createClient } from "@supabase/supabase-js";

const supabaseUrl = process.env.NEXT_PUBLIC_SUPABASE_API;
const supabaseKey = process.env.NEXT_PUBLIC_SUPABASE_KEY;

if (!supabaseKey || !supabaseUrl) {
  throw new Error(
    "A chave do Supabase não está definida. Verifique suas variáveis de ambiente."
  );
}

export const supabase = createClient(supabaseUrl, supabaseKey);
