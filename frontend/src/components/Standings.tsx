"use client"
import { LucideSearch } from 'lucide-react'
import React, { Suspense, useEffect, useState } from 'react'
import StandingTable from './StandingTable'
import { Standing, Table } from '@/types/leagueTypes'
import Image from 'next/image'
import Link from 'next/link'

type Props = {}

const Standings = (props: Props) => {
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
    const [selectedLeague, setSelectedLeague] = useState("BSA");
    const [table, setTable] = useState<Table[] | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [matchDay, setMatchDay] = useState();
    const [competition, setCompetition] = useState<any>(null);


    useEffect(() => {
        setLoading(true);
        setError(null);

        fetch(`${process.env.NEXT_PUBLIC_API_BASE_URL}/championship/${selectedLeague}/standings`)
            .then((res) => {
                if (!res.ok) {
                    throw new Error(`HTTP error! status: ${res.status}`);
                }
                return res.json();
            })
            .then((data) => {
                setCompetition(data.competition);
                setTable(data.standings[0].table);
                setMatchDay(data.season.currentMatchday);
                setLoading(false);
            })
            .catch((err) => {
                setError(err.message || 'Something went wrong');
                setLoading(false);
            });
    }, [selectedLeague]);




    const handleLeagueChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        console.log(event)
        setSelectedLeague(event.target.value);
    };
    const renderTable = () => {
        if (loading) return <div>Loading standings...</div>;
        if (error) return <div>Error loading standings: {error}</div>;
        if (matchDay === 0) return <div>Campeonato não começou ainda</div>
        if (table) return (
            <>
                <StandingTable league={selectedLeague} table={table} />
                <Link href={`/jogos?league=${selectedLeague}`}>
                    <button type="button" className="text-white bg-gradient-to-r from-green-400 via-green-500 to-green-600 mt-4 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-base px-5 py-2.5 text-center me-2 mb-2">Partidas</button>
                </Link>
            </>

        )

    }
    return (
        <section className='w-[90%] md:w-[80%] my-10 mx-auto min-h-[600px] text-black'>
            <h1 className='font-bold text-4xl'>Tabela das Ligas</h1>
            <div className='my-10 relative'>
                <select
                    value={selectedLeague}
                    onChange={handleLeagueChange}
                    className='h-10 w-1/2 md:w-1/3 focus:outline-none pl-2 text-slate-900 bg-slate-100 rounded-md'
                >
                    {(Object.entries(leagues).map(([code, name]) => (
                        <option key={code} value={code}>
                            {name}
                        </option>
                    )))}
                </select>
            </div>
            {renderTable()}



        </section>
    );
};

export default Standings;