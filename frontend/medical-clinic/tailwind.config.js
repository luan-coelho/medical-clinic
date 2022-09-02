module.exports = {
  darkMode: 'class',
  content: [
    './index.html',
    './src/**/*.{vue,js,ts,jsx,tsx}',
    './node_modules/flowbite/**/*.js',
  ],
  theme: {
    screens: {
      sx: '0px',
      sm: '640px',
      md: '768px',
      lg: '1024px',
      xl: '1280px',
      '2xl': '1536px',
    },
    colors: {
      'root-color': {
        dark: '#131212',
        litledark: '#2C2C2C',
      },
    },
  },
  plugins: [require('flowbite/plugin')],
};
