import React from 'react'

const App: React.FC = () => {
  return (
    <section className="bg-white dark:bg-gray-900 h-full">
      <div className="py-8 px-4 mx-auto max-w-screen-xl text-center lg:py-16 lg:px-12">
 
        <h1 className=" mb-4 text-4xl font-extrabold tracking-tight leading-none text-gray-900 md:text-5xl lg:text-6xl dark:text-white">
          面试邀请函
        </h1>
        <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
          XX 先生/女士：
        </p>

        <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
          您好！
          我司人力资源部已初审您的简历，经过初步沟通，认为您基本具备XXX岗位的任职资格，因此正式通知您来我公司参加面试。
        </p>
        <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
          面试时间：xxxxx
        </p>
        <div className="flex flex-col mb-4 lg:mb-16 space-y-4 sm:flex-row sm:justify-center sm:space-y-0 sm:space-x-4">
          <a
            href="#"
            className="inline-flex justify-center items-center py-3 px-5 text-base font-medium text-center text-gray-900 rounded-lg border border-gray-300 hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 dark:text-white dark:border-gray-700 dark:hover:bg-gray-700 dark:focus:ring-gray-800"
          >
            拒绝
          </a>
          <a
            href="#"
            className="inline-flex justify-center items-center py-3 px-5 text-base font-medium text-center text-white rounded-lg bg-primary-700 hover:bg-primary-800 focus:ring-4 focus:ring-primary-300 dark:focus:ring-primary-900"
          >
            接受
          </a>
        </div>
      </div>
    </section>
  )
}

export default App
