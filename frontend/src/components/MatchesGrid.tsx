"use client"
import React, { useEffect, useState } from 'react'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from './ui/card'
import MatchCard from './MatchCard'
import { Match } from '@/types/leagueTypes'
import { match } from 'assert'

type Props = {
    matches: Match[]
}

const MatchesGrid = ({ matches }: Props) => {
    const [matchday, setMatchday] = useState(matches[matches.length - 1].matchday);
    const [filteredMatches, setFilteredMatches] = useState<Match[]>(matches.filter(match => match.matchday === 1));

    const uniqueMatchdays = Array.from(new Set(matches.map(match => match.matchday)));

    const matchdaysButtons = uniqueMatchdays.map((uniqueMatchday, index) => (
        <button 
            key={index} 
            onClick={(e) => handleClick(e)} 
            className={`w-12 ${uniqueMatchday == matchday? "bg-[#4242c4]" : ""} text-xl rounded-md border-[#4242c4] border-2 h-12 hover:bg-[#4242c4] transform`}>
            {uniqueMatchday}
        </button>
    ));
    useEffect(() => {
        const changeMatches = () => {
            const newFilteredMatches: Match[] = matches.filter((match: any) => match.matchday === matchday);
            setFilteredMatches(newFilteredMatches);
        };

        changeMatches();
    }, [matchday]);
    const increaseMatchday = () => setMatchday(prev => prev < matches[matches.length - 1]?.matchday ? prev + 1 : prev);
    const decreaseMatchday = () => setMatchday(prev => prev > 1 ? prev - 1 : 1);

    const handleClick = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        const button = e.target as HTMLButtonElement
        setMatchday(Number(button.innerHTML))
    }

    return (
        <div className=''>
            <div className='flex gap-4 justify-center md:gap-8 items-center'>
                <h1 className='text-xl font-bold'>Rodada: </h1>
                <div className='flex w-[70%] flex-wrap gap-2 md:gap-3 '>
                    {matchdaysButtons}      
                </div>
            </div>

            <div className='gap-4 items-center justify-center my-4 mx-auto overflow-scroll flex'>
                <div className='grid grid-cols-1 md:grid-cols-3 gap-4'>
                    {filteredMatches.map((match, index) => (
                        <MatchCard key={index} match={match} />
                    ))}
                </div>
            </div>
        </div>

    )
}

export default MatchesGrid