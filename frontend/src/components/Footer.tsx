import Image from 'next/image'
import Link from 'next/link'
import React from 'react'
import logo from '../img/logo.png'
import { Github, Instagram, Linkedin, LinkedinIcon, LucideLinkedin } from 'lucide-react'


type Props = {}

const Footer = (props: Props) => {
    return (
        <footer className="bg-[#125f12] text-gray-300 shadow">
            <div className="w-full max-w-screen-xl mx-auto p-4 ">
                <div className="sm:flex gap-4 justify-between">
                    <div className='p-3 flex text-3xl font-semibold text-white'>
                        <Link href='/'>
                            <Image src={logo} alt='TraversyPress' width={40} />
                        </Link>
                        <span className='mt-1 ml-3'>Brasileirão</span>
                    </div>
                    <div className='flex text-lg gap-4 flex-col'>
                        <Link href="/sobre" className='cursor-pointer hover:border-b-2 h-8'>Sobre nós</Link>
                        <span className='cursor-pointer hover:border-b-2 h-8'>Termo de compromisso</span>
                        <span className='cursor-pointer hover:border-b-2 h-8'>Termo de privacidade</span>
                    </div>
                    <div className='flex font-semibold  flex-col'>
                        <h1 className='text-3xl'>Conheça os criadores</h1>
                        <hr className='mt-2'></hr>
                        <div className='flex justify-between my-2'>
                            <h2 className='text-xl '>Matheus Lustosa</h2>
                            <div className='flex gap-3'>
                                <Link  rel="noopener noreferrer" target="_blank" href='https://github.com/matheusLustosa23'><Github /> </Link>
                                <Link  rel="noopener noreferrer" target="_blank" href='/'><Instagram /> </Link>
                                <Link  rel="noopener noreferrer" target="_blank" href='https://www.linkedin.com/in/matheus-lustosa-8472391a3/' ><LinkedinIcon /> </Link>
                            </div>
                        </div>
                        <div className='flex justify-between my-2'>
                            <h2 className='text-xl '>Guilherme Vale</h2>
                            <div className='flex gap-3'>
                                <Link  rel="noopener noreferrer" target="_blank" href='https://github.com/Guilherme-Vale-98'><Github /> </Link>
                                <Link  rel="noopener noreferrer" target="_blank" href='/'><Instagram /> </Link>
                                <Link  rel="noopener noreferrer" target="_blank" href='/' ><LinkedinIcon /> </Link>
                            </div>
                        </div>
                    </div>


                </div>
                <hr className="my-6 border-gray-200 sm:mx-auto dark:border-gray-700 " />
                <span className="block text-sm text-gray-300 sm:text-center dark:text-gray-400">© 2024<a href="https://flowbite.com/" className="hover:underline"> Brasileirão™</a>. Este site é um case de estudo.</span>
            </div>
        </footer>
    )
}

export default Footer