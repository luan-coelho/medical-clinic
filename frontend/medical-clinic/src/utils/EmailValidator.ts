export default function emailValidate(): boolean {
  const email = (document.getElementById('email') as HTMLInputElement).value;
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    (document.getElementById('email') as HTMLInputElement).value = '';
    return false;
  }
  return true;
}
