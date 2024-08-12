import Image from 'next/image'
import React from 'react'
import estadio from '../img/estadio.jpg'
import brasao from '../img/brasao.png'

type Props = {}

const Hero = (props: Props) => {
    return (
        <section className='relative h-[330px] flex'>
            <div className='-z-10 absolute w-full h-[330px] '>
                <Image src={estadio}
                    layout="fill"
                    quality={100} alt='estádio' />
            </div>
            <div className='w-full sm:h-[300px]  sm:flex justify-end '>
                    <Image src={brasao} alt='brasao do campeonato'  width={600} quality={100}></Image>
            </div>
        </section>
    )
}

export default Hero