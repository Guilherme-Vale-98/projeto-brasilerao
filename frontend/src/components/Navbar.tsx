import React from 'react'
import logo from '../img/logo.png'
import Link from 'next/link'
import Image from 'next/image'
type Props = {}



const Navbar = (props: Props) => {
    return (
        <nav className="bg-[#125f12]">
            <div className='p-3 mx-auto w-[80%] flex items-center  text-3xl font-semibold text-white'>
                <Link href='/'>
                    <Image src={logo} alt='TraversyPress' width={40} />
                </Link>
                <span className='mt-1 ml-3'>Brasileirão</span>
            </div>
        </nav>
    )
}

export default Navbar