import { NextRequest, NextResponse } from "next/server";

export function middleware(req: NextRequest) {
  const { pathname } = req.nextUrl;

  if (pathname === "/register") {
    console.log("Teste");
  }

  return NextResponse.next();
}
