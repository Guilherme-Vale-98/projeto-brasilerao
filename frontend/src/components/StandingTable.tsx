import { Standing, Table } from '@/types/leagueTypes';
import { LucideCheck, LucideMinus, LucideX, LucideXCircle } from 'lucide-react';
import Image from 'next/image';
import React from 'react';

const processFormerMatches = (matchResult: string) =>{
    switch (matchResult) {
        case 'W':
          return <LucideCheck size={20} className='p-1 bg-green-600 text-white rounded-full'/>;
        case 'D':
          return <LucideMinus size={20} className='p-1 bg-gray-600 text-white rounded-full'/>;
        case 'L':
          return <LucideX size={20} className='p-1 bg-red-600 text-white rounded-full'/>;
        default:
          return matchResult;
      }
}

const StandingTable = async () => {
    const res = await fetch('http://localhost:8080/api/championship/BSA/standingsWithMatches');
    const data = await res.json();

    const standings: Standing = data.standings[0]
    const table = standings.table
    table.map
    return (
        <div className="">
            <table className="min-w-full bg-white">
                <thead className="bg-gray-800 h-4 text-white">
                    <tr>
                        <th className="w-1/15 py-2">Posição</th>
                        <th className="w-3/15 text-left py-2">Time</th>
                        <th className="w-1/15 py-2">P</th>
                        <th className="w-1/15 py-2">J</th>
                        <th className="w-1/15 py-2">V</th>
                        <th className="w-1/15 py-2">E</th>
                        <th className="w-1/15 py-2">D</th>
                        <th className="w-1/15 py-2">GP</th>
                        <th className="w-1/15 py-2">GC</th>
                        <th className="w-1/15 py-2">SG</th>
                        <th className="w-1/15 py-2">%</th>
                        <th className="w-3/15 py-2">Últ.Jogos</th>
                    </tr>
                </thead>
                <tbody>
                    {table.map((tableItem: Table, index: number) => (
                        <tr key={index} className="text-center border-b odd:bg-slate-200">
                            <td className="py-2">{tableItem.position}</td>
                            <td className="py-2 font-bold flex text-left">
                                <Image width={24} className='mr-3' height={10} src={tableItem.team.crest} alt={`escudo do ${tableItem.team.shortName}`}></Image>
                                {tableItem.team.shortName}
                            </td>
                            <td className="py-2">{tableItem.points}</td>
                            <td className="py-2">{tableItem.playedGames}</td>
                            <td className="py-2">{tableItem.won}</td>
                            <td className="py-2">{tableItem.draw}</td>
                            <td className="py-2">{tableItem.lost}</td>
                            <td className="py-2">{tableItem.goalsFor}</td>
                            <td className="py-2">{tableItem.goalsAgainst}</td>
                            <td className="py-2">{tableItem.goalsFor - tableItem.goalsAgainst}</td>
                            <td className="py-2">{(tableItem.won / tableItem.playedGames * 100).toFixed(2)}%</td>
                            <td className="py-2 flex justify-center gap-1">{tableItem.formerMatches.slice(-5).map(result => processFormerMatches(result))}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default StandingTable;