import MatchesGrid from '@/components/MatchesGrid'
import { Match } from '@/types/leagueTypes';
import React from 'react'

type Props = {}

const page = async (props: Props) => {

  const res = await fetch('http://localhost:8023/api/championship/BSA/standings/matches',
    { next: { revalidate: 60*30 } }
  );
  const data = await res.json();
  const matches: Match[] = data.matches


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