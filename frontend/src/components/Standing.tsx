import { LucideSearch } from 'lucide-react'
import React from 'react'
import StandingTable from './StandingTable'

type Props = {}

const Standing = (props: Props) => {
    return (
        <section className='w-[80%] my-10 mx-auto min-h-[600px] text-black'>
            <h1 className='font-bold text-4xl'>Tabela do Campeonato</h1>
            <div className='my-10 relative'>
                <LucideSearch className='absolute text-slate-400 ml-1 top-2'/>
                <input placeholder='Busque por um time' className='h-10 w-full
                 focus:outline-none pl-8 text-slate-900 bg-slate-100 rounded-md'></input>
            </div>
            <StandingTable/>
        </section>
    )
}

export default Standing