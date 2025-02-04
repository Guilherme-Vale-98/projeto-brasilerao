export interface Team {
  id: number
  shortName: string
  tla: string
  crest: string
}

export interface Table {
    position: number
    team: Team
    playedGames: number
    won: number
    draw: number
    lost: number
    points: number
    goalsFor: number
    goalsAgainst: number
    goalsDifference: number
    formerMatches: string[]
  }
  export interface Standing {
    emblem: string
    table: Table[]
  }

  export interface Match {
    matchday: number
    score: Score
    homeTeam: Team
    awayTeam: Team
    utcDate: Date
  }
  
  export interface Score {
    winner: string
    fullTime: FullTime
  }
  
  export interface FullTime {
    home: number
    away: number
  }
  