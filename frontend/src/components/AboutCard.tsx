import { Github, Instagram, LinkedinIcon } from 'lucide-react'
import React from 'react'
import { Card, CardHeader, CardContent, CardFooter } from './ui/card'
import Link from 'next/link'

type Props = {}

const AboutCard = (props: Props) => {
    return (
        <Card className='w-1/3'>
            <CardHeader className=''>
                <div className='h-[200px] mb-4 w-[200px] left-1/2 -translate-x-1/2 relative
        bg-black rounded-full'>
                </div>

                <span className='border-t-2 pt-8 border-gray-600  font-semibold text-gray-600 text-center text-2xl'>Guilherme Vale</span>
            </CardHeader>
            <CardContent className='border-b-2 mx-4  border-gray-600'>
                Desenvolvedor fullstack atuou neste projeto construindo as interfaces de usuário utilizando next.js 14 com typescript, também trabalhou no design e construção da API no backend.
            </CardContent>
            <CardFooter>
                <div className='flex w-full mt-4 items-center justify-between'>
                    <button className='h-14 p-4 font-bold border-2 border-black hover:bg-black hover:text-white rounded-md'>PORTFOLIO</button>
                    <div className='flex gap-3'>
                        <Link href='https://github.com/Guilherme-Vale-98'><Github /> </Link>
                        <Link href='/'><Instagram /> </Link>
                        <Link href='/' ><LinkedinIcon /> </Link>
                    </div>
                </div>
            </CardFooter>
        </Card>
    )
}

export default AboutCard