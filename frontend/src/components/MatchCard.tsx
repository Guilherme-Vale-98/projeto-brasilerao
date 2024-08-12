import React from 'react'
import { Card, CardHeader, CardTitle, CardDescription, CardContent, CardFooter } from './ui/card'
import { Match, Score, Team } from '@/types/leagueTypes'
import bola from "../img/bola.png"
import Image from 'next/image'

type Props = {
    match: Match
}

const MatchCard = ({ match }: Props) => {
    const { homeTeam, awayTeam, score, matchday, utcDate } = match
    const date = new Date(utcDate)

    return (
        <Card className=' overflow-hidden border-[#0e4515] relative w-[24rem] text-white h-48 py-4 card-gradient ' >
            <div className='absolute w-[150px] h-[150px] left-[300px]  top-[-55px]'>
                <Image width={150} className='relative' height={10} src={bola} alt={`escudo do flamengo`}></Image>
            </div>
            <div className='absolute w-[150px] h-[150px] left-[120px]  top-[130px]'>
                <Image width={150} className='relative' height={10} src={bola} alt={`escudo do flamengo`}></Image>
            </div>
            <div className='text-lg text-center'>
                {date.toLocaleString("pt-br").slice(0, 10)}
            </div>
            <CardContent>
                <div className='w-1/3 text-center'>
                    <Image width={55} className='mb-2 relative left-1/2 -translate-x-1/2' height={10} src={homeTeam.crest} alt={`escudo do flamengo`}></Image>
                    <span className='text-bs font-semibold'>{homeTeam.shortName}</span>
                </div>
                <div className='min-w-[33.333333%] h-24   flex items-center justify-center text-3xl font-semibold'>{score.fullTime.home} - {score.fullTime.away}</div>
                <div className='w-1/3  text-center '>
                    <Image width={55} className='mb-2 relative left-1/2 -translate-x-1/2' height={10} src={awayTeam.crest} alt={`escudo do flamengo`}></Image>
                    <span className='text-bs w-1/2 font-semibold'>{awayTeam.shortName}</span>
                </div>
            </CardContent>
        </Card>
    )
}

export default MatchCard