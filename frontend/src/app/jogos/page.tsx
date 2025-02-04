import MatchesGrid from '@/components/MatchesGrid'
import { Match } from '@/types/leagueTypes';
import { redirect } from 'next/navigation';
import React from 'react'

type Props = {
  searchParams: { [key: string]: string | string[] | undefined };
}

const page = async ({searchParams}: Props) => {

  const leagues = {
    BSA: "Campeonato Brasileiro Série A",
    PL: "Premier League",
    ELC: "English League",
    CL: "UEFA Champions League",
    FL1: "Ligue 1",
    BL1: "Bundesliga",
    SA: "Campeonato Italiano Serie A",
    DED: "Eredivisie",
    PPL: "Primeira Liga",
    CLI: "Copa Libertadores",
    PD: "Primera Division",
};
const league = searchParams.league;
if(typeof league !== "string" || !Object.keys(leagues).includes(league)){
  redirect('/');
  }
  
  const res = await fetch(`http://localhost:8080/api/championship/${league}/standings/matches`,
    { next: { revalidate: 60*30 } }
  );
  const data = await res.json();
  const matches: Match[] = data.matches
  if(matches.length <= 0){
    redirect("/")
  }

  return (
    <div className='bg-[#1B1D25] py-10'>
      <section className='w-[80%] text-white mx-auto '>
        <h1 className='text-3xl text-center font-bold mb-4'>Jogos da temporada 2024</h1>
        <div>
          <MatchesGrid matches={matches}/>
        </div>
      </section>
    </div>
  )
}

export default page