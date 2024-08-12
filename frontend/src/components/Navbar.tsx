import React from 'react'
import logo from '../img/logo.png'
import Link from 'next/link'
import Image from 'next/image'
type Props = {}



const Navbar = (props: Props) => {
    return (
        <nav className="bg-[#125f12]">
            <div className='p-3 mx-auto text-white sm:w-[80%] flex justify-between'>
                <div className='flex items-center text-3xl font-semibold '>
                    <Link href='/'>
                        <Image src={logo} alt='TraversyPress' width={40} />
                    </Link>
                    
                </div>
                <div className='flex gap-3 sm:gap-0 text-lg items-center justify-around  sm:w-1/4'>
                    <Link className='hover:text-[#7bd67b]' href="/">Início</Link>
                    <Link className='hover:text-[#7bd67b]' href="/jogos">Partidas</Link>
                    <Link className='hover:text-[#7bd67b]' href="/sobre">Sobre nós</Link>
                </div>
            </div>
        </nav>
    )
}

export default Navbar