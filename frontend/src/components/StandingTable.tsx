import React from 'react';

const StandingTable = () => {
  const standings = [
    {
      position: 1,
      team: 'Team A',
      points: 45,
      played: 20,
      wins: 14,
      draws: 3,
      losses: 3,
      goalFavor: 35,
      goalsAgainst: 20,
      recentMatches: ['W', 'D', 'W', 'L', 'W']
    },
    {
      position: 2,
      team: 'Team B',
      points: 42,
      played: 20,
      wins: 13,
      draws: 3,
      losses: 4,
      goalFavor: 30,
      goalsAgainst: 20,
      recentMatches: ['W', 'W', 'W', 'D', 'L']
    }
  ];

  return (
    <div className="">
      <table className="min-w-full bg-white">
        <thead className="bg-gray-800 h-4 text-white">
          <tr>
            <th className="w-1/12 py-2">Posição</th>
            <th className="w-3/12 py-2">Time</th>
            <th className="w-1/12 py-2">P</th>
            <th className="w-1/12 py-2">J</th>
            <th className="w-1/12 py-2">V</th>
            <th className="w-1/12 py-2">E</th>
            <th className="w-1/12 py-2">D</th>
            <th className="w-1/12 py-2">GP</th>
            <th className="w-1/12 py-2">GC</th>
            <th className="w-1/12 py-2">SG</th>
            <th className="w-3/12 py-2">Últ.Jogos</th>
          </tr>
        </thead>
        <tbody>
          {standings.map((team, index) => (
            <tr key={index} className="text-center border-b odd:bg-slate-200">
              <td className="py-2">{team.position}</td>
              <td className="py-2">{team.team}</td>
              <td className="py-2">{team.points}</td>
              <td className="py-2">{team.played}</td>
              <td className="py-2">{team.wins}</td>
              <td className="py-2">{team.draws}</td>
              <td className="py-2">{team.losses}</td>
              <td className="py-2">{team.goalFavor}</td>
              <td className="py-2">{team.goalsAgainst}</td>
              <td className="py-2">{team.goalFavor - team.goalsAgainst}</td>
              <td className="py-2">
                <div className="flex justify-center">
                  {team.recentMatches.map((match, idx) => (
                    <span key={idx} className="px-1">
                      {match}
                    </span>
                  ))}
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default StandingTable;