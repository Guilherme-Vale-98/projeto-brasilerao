import { Github, Instagram, LinkedinIcon } from 'lucide-react'
import React from 'react'
import { Card, CardHeader, CardContent, CardFooter } from './ui/card'
import Link from 'next/link'
import Image, { StaticImageData } from 'next/image'


type SocialLinks = {
    githubURL: string
    linkedinURL: string
    instaURL: string
}

type Props = {
    name: string
    description: string
    socialLinks: SocialLinks
    portrait: StaticImageData
}

const AboutCard = ({name, description, socialLinks, portrait}: Props) => {
    return (
        <Card className='w-96'>
            <CardHeader className=''>
                <Image src={portrait} alt="Retrato Guilherme" className='h-[200px] mb-4 w-[200px] left-1/2 object-cover object-[center_40%] -translate-x-1/2 relative
        border-4 shadow-xl border-black rounded-full'>
                </Image>

                <span className='border-t-2 pt-8  border-gray-600 font-semibold text-gray-600 text-center text-2xl'>{name}</span>
            </CardHeader>
            <CardContent className='border-b-2 overflow-scroll  h-[200px] mx-4  border-gray-600'>
                {description}
            </CardContent>    
            <CardFooter>
                <div className='flex w-full mt-4 items-center justify-between'>
                    <button className='h-10 p-2 sm:h-14 sm:p-4 font-bold border-2 border-black hover:bg-black hover:text-white rounded-md'>PORTFOLIO</button>
                    <div className='flex gap-2'>
                        <div className='hover:bg-black rounded-full flex items-center justify-center w-10 h-10 hover:text-white'>
                            <Link target="_blank" rel="noopener noreferrer" href={socialLinks.githubURL} ><Github size={30} /> </Link>
                        </div>
                        <div className='hover:bg-black rounded-full flex items-center justify-center w-10 h-10 hover:text-white'>
                            <Link target="_blank" rel="noopener noreferrer" href={socialLinks.instaURL}><Instagram size={30}/> </Link>
                        </div>
                        <div className='hover:bg-black rounded-full flex items-center justify-center w-10 h-10 hover:text-white'>
                            <Link target="_blank" rel="noopener noreferrer" href={socialLinks.linkedinURL} ><LinkedinIcon size={30}/> </Link>
                        </div>
                    </div>
                </div>
            </CardFooter>
        </Card>
    )
}

export default AboutCard