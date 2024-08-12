import AboutCard from '@/components/AboutCard'
import { Card, CardContent, CardFooter, CardHeader } from '@/components/ui/card'
import { Github, GithubIcon, Instagram, LinkedinIcon } from 'lucide-react'
import Link from 'next/link'
import React from 'react'

type Props = {}

const sobre = (props: Props) => {
    return (
        <section className='w-[80%] mx-auto '>
            <div className='mt-6'>
                <h1 className='text-4xl mb-4 text-center w-full font-bold'>Conheça os criadores</h1>
            </div>
            <div className='flex justify-center my-6 gap-4'>
                <AboutCard/>
                <Card className='w-1/3'>
                    <CardHeader className=''>
                        <div className='h-[200px] mb-4 w-[200px] left-1/2 -translate-x-1/2 relative
                        bg-black rounded-full'>
                        </div>
                        
                        <span className='border-t-2 pt-8 border-gray-600  font-semibold text-gray-600 text-center text-2xl'>Matheus Lustosa</span>
                    </CardHeader>
                    <CardContent className='border-b-2 mx-4  border-gray-600'>
                        
                    </CardContent>
                    <CardFooter>
                        <div className='flex w-full mt-4 items-center justify-between'>
                            <button className='h-14 p-4 font-bold border-2 border-black hover:bg-black hover:text-white rounded-md'>PORTFOLIO</button>
                            <div className='flex gap-3'>
                                <Link href='/'><Github/> </Link>
                                <Link href='/'><Instagram/> </Link>
                                <Link href='/' ><LinkedinIcon/> </Link>
                            </div>
                        </div>
                    </CardFooter>
                </Card>
            </div>

        </section>
    )
}

export default sobre