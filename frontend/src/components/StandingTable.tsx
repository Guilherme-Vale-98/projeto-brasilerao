"use client";
import React, { useEffect, useState } from 'react';
import { Standing, Table } from '@/types/leagueTypes';
import { LucideCheck, LucideMinus, LucideX } from 'lucide-react';
import Image from 'next/image';

const processFormerMatches = (matchResult: string) => {
  switch (matchResult) {
    case 'W':
      return <LucideCheck size={20} className="p-1 bg-green-600 text-white rounded-full" />;
    case 'D':
      return <LucideMinus size={20} className="p-1 bg-gray-600 text-white rounded-full" />;
    case 'L':
      return <LucideX size={20} className="p-1 bg-red-600 text-white rounded-full" />;
    default:
      return matchResult;
  }
};

type Props = {
  league: string;
  table: Table[];
};

const StandingTable = ({ league, table }: Props) => {


  return (
    <div className="overflow-x-auto">
      <table className="min-w-full bg-white">
        <thead className="bg-gray-800 h-4 text-white">
          <tr>
            <th className="py-2 px-4 whitespace-nowrap">Posição</th>
            <th className="text-left py-2 px-4 whitespace-nowrap">Time</th>
            <th className="py-2 px-4 whitespace-nowrap">P</th>
            <th className="py-2 px-4 whitespace-nowrap">J</th>
            <th className="py-2 px-4 whitespace-nowrap">V</th>
            <th className="py-2 px-4 whitespace-nowrap">E</th>
            <th className="py-2 px-4 whitespace-nowrap">D</th>
            <th className="py-2 px-4 whitespace-nowrap">GP</th>
            <th className="py-2 px-4 whitespace-nowrap">GC</th>
            <th className="mobile-hidden py-2 px-4 whitespace-nowrap">SG</th>
            <th className="mobile-hidden py-2 px-4 whitespace-nowrap">%</th>
            <th className="py-2 px-4 whitespace-nowrap">Últ.Jogos</th>
          </tr>
        </thead>
        <tbody>
          {table.map((tableItem: Table, index: number) => (
            <tr key={index} className="text-center border-b odd:bg-slate-200">
              <td className="py-2 px-4 whitespace-nowrap">{tableItem.position}</td>
              <td className="py-2 px-4 font-bold flex text-left whitespace-nowrap">
                <Image
                  width={24}
                  height={10}
                  className="mr-3"
                  src={tableItem.team.crest}
                  alt={`escudo do ${tableItem.team.shortName}`}
                />
                <span className="hidden sm:inline">{tableItem.team.shortName}</span>
              </td>
              <td className="py-2 px-4 whitespace-nowrap">{tableItem.points}</td>
              <td className="py-2 px-4 whitespace-nowrap">{tableItem.playedGames}</td>
              <td className="py-2 px-4 whitespace-nowrap">{tableItem.won}</td>
              <td className="py-2 px-4 whitespace-nowrap">{tableItem.draw}</td>
              <td className="py-2 px-4 whitespace-nowrap">{tableItem.lost}</td>
              <td className="py-2 px-4 whitespace-nowrap">{tableItem.goalsFor}</td>
              <td className="py-2 px-4 whitespace-nowrap">{tableItem.goalsAgainst}</td>
              <td className="mobile-hidden py-2 px-4 whitespace-nowrap">
                {tableItem.goalsFor - tableItem.goalsAgainst}
              </td>
              <td className="mobile-hidden py-2 px-4 whitespace-nowrap">
                {(tableItem.won / tableItem.playedGames * 100).toFixed(2)}%
              </td>
              <td className="py-2 px-4 flex justify-center gap-1 whitespace-nowrap">
                {tableItem.formerMatches.slice(-5).map((result, idx) => (
                  <React.Fragment key={idx}>{processFormerMatches(result)}</React.Fragment>
                ))}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default StandingTable;
