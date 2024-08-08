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
    table: Table[]
  }