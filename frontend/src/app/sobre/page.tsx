import AboutCard from '@/components/AboutCard'
import { Card, CardContent, CardFooter, CardHeader } from '@/components/ui/card'
import { Github, GithubIcon, Instagram, LinkedinIcon } from 'lucide-react'
import Link from 'next/link'
import React from 'react'
import gui from '../../img/gui.jpg'
import matheus from '../../img/matheus.png'
type Props = {}

const sobre = (props: Props) => {
    
    const guilhermeDescription = "Desenvolvedor fullstack, atuou neste projeto construindo as interfaces de usuário utilizando Next.js com typescript. Também trabalhou no design e construção da API que serviu os dados para a presente aplicação."
    
    const matheusDescription = "Desenvolvedor Backend, atuou no planejamento e análise de requisitos, na implementação, no desenvolvimento da API. Construiu o backend deste projeto utilizando técnicas modernas com Java 21 e Spring Boot 3."
    
    return (
        <section className='w-[80%] mx-auto '>
            <div className='mt-6'>
                <h1 className='text-4xl mb-4 text-center w-full font-bold'>Conheça os criadores</h1>
            </div>
            <div className='flex flex-wrap justify-center my-6 gap-4'>
                <AboutCard name={'Guilherme Vale'} description={guilhermeDescription} socialLinks={{
                    githubURL: 'https://github.com/Guilherme-Vale-98',
                    linkedinURL: '',
                    instaURL: ''
                }} portrait={gui}/>
                <AboutCard name={'Matheus Lustosa'} description={matheusDescription} socialLinks={{
                    githubURL: 'https://github.com/matheusLustosa23',
                    linkedinURL: 'https://www.linkedin.com/in/matheus-lustosa-8472391a3/',
                    instaURL: ''
                }} portrait={matheus}/>
            </div>

        </section>
    )
}

export default sobre






